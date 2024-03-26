package com.app.library.controller;

import com.app.library.model.dto.BorrowDto;
import com.app.library.service.BorrowService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/borrows")
@AllArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping
    ResponseEntity<List<BorrowDto>> getAllBorrows() {
        List<BorrowDto> allBorrows = borrowService.getAllBorrows();
        if (!allBorrows.isEmpty()) {
            return new ResponseEntity<>(allBorrows, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<BorrowDto> getBorrowById(@PathVariable UUID id) {
        return borrowService.getBorrowById(id)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<BorrowDto> saveBorrow(@Valid @RequestBody BorrowDto Borrow) {
        BorrowDto savedBorrow = borrowService.saveBorrow(Borrow);
        return new ResponseEntity<>(savedBorrow, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<BorrowDto> replaceBorrow(@PathVariable UUID id, @Valid @RequestBody BorrowDto Borrow) {
        return borrowService.replaceBorrow(id, Borrow)
                .map(d -> new ResponseEntity<>(d, HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBorrow(@PathVariable UUID id) {
        borrowService.deleteBorrow(id);
        return new ResponseEntity<>("Book Borrow has been deleted.", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    ResponseEntity<List<BorrowDto>> searchBorrows(@RequestParam(value = "userId") UUID id) {
        List<BorrowDto> searchedBorrows = borrowService.getBorrowsByUser(id);
        if (!searchedBorrows.isEmpty()) {
            return new ResponseEntity<>(searchedBorrows, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }
}
