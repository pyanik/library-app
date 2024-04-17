package com.app.library.service;

import com.app.library.exception.BookAlreadyBorrowedException;
import com.app.library.exception.BookNotFoundException;
import com.app.library.exception.BorrowNotFoundException;
import com.app.library.model.dto.BookDto;
import com.app.library.model.dto.BorrowDto;
import com.app.library.model.entity.BorrowEntity;
import com.app.library.model.enums.BorrowStatus;
import com.app.library.model.mapper.BorrowMapper;
import com.app.library.persistence.BorrowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMapper borrowMapper;
    private final BookService bookService;

    public List<BorrowDto> getAllBorrows() {
        return borrowRepository.findAll().stream()
                .map(borrowMapper::toDto)
                .toList();
    }

    public Optional<BorrowDto> getBorrowById(UUID BorrowId) {
        return borrowRepository.findById(BorrowId)
                .map(borrowMapper::toDto);
    }

    @Transactional(rollbackFor = BookAlreadyBorrowedException.class)
    public List<BorrowDto> saveBorrows(List<BorrowDto> borrows) throws BookAlreadyBorrowedException {
        ArrayList<BorrowDto> borrowDtos = new ArrayList<>();
        for (BorrowDto borrow : borrows) {
            borrowDtos.add(saveBorrow(borrow));
        }
        return borrowDtos;
    }

    public Optional<BorrowDto> replaceBorrow(UUID borrowId, BorrowDto borrowDto) {
        if (!borrowRepository.existsById(borrowId)) {
            return Optional.empty();
        }
        
        BorrowEntity borrowToUpdate = borrowMapper.toEntity(borrowDto);
        if (isAlreadyReturned(borrowToUpdate)) {
            borrowToUpdate.setDateOfReturn(LocalDateTime.now());
        }
        BorrowEntity updatedBorrow = borrowRepository.save(borrowToUpdate);
        return Optional.of(borrowMapper.toDto(updatedBorrow));
    }

    public void deleteBorrow(UUID borrowId) {
        borrowRepository.deleteById(borrowId);
    }

    public List<BorrowDto> getBorrowsByUser(UUID userId) {
        return borrowRepository.findAllByUserId(userId).stream()
                .map(borrowMapper::toDto)
                .toList();
    }

    @Transactional
    public void returnAllBookForUser(UUID userId) {
        borrowRepository.findAllByUserId(userId).stream()
                .filter(e -> BorrowStatus.BORROWED.equals(e.getBorrowStatus()))
                .forEach(e -> setReturnStatus(e.getId()));
    }

    private void setReturnStatus(UUID borrowId) {
        BorrowEntity ReturnStatusBorrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException(borrowId));
        ReturnStatusBorrow.setDateOfReturn(LocalDateTime.now());
        ReturnStatusBorrow.setBorrowStatus(BorrowStatus.RETURNED);
        borrowRepository.save(ReturnStatusBorrow);
    }

    private BorrowDto saveBorrow(BorrowDto borrowDto) throws BookAlreadyBorrowedException {
        UUID bookId = borrowDto.bookId();
        if (isBookAlreadyBorrowed(bookId)) {
            throw new BookAlreadyBorrowedException();
        }

        BorrowEntity borrowToSave = borrowMapper.toEntity(borrowDto);
        borrowToSave.setDateOfBorrow(LocalDateTime.now());
        borrowToSave.setBorrowStatus(BorrowStatus.BORROWED);
        BorrowEntity savedBorrow = borrowRepository.save(borrowToSave);
        return borrowMapper.toDto(savedBorrow);
    }

    private boolean isBookAlreadyBorrowed(UUID bookId) {
        Optional<BookDto> bookById = bookService.getBookById(bookId);
        List<BorrowDto> bookBorrows = bookById.orElseThrow(() -> new BookNotFoundException(bookId)).borrows();
        return bookBorrows.stream()
                .anyMatch(b -> BorrowStatus.BORROWED.equals(b.borrowStatus()));
    }

    private boolean isAlreadyReturned(BorrowEntity borrowEntity) {
        return BorrowStatus.RETURNED.equals(borrowEntity.getBorrowStatus()) && borrowEntity.getDateOfReturn() != null;
    }
}
