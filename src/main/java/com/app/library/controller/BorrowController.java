package com.app.library.controller;

import com.app.library.model.dto.BorrowDto;
import com.app.library.service.BorrowService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
            return ResponseEntity.ok(allBorrows);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<BorrowDto> getBorrowById(@PathVariable UUID id) {
        return borrowService.getBorrowById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<BorrowDto> saveBorrow(@Valid @RequestBody BorrowDto Borrow) {
        BorrowDto savedBorrow = borrowService.saveBorrow(Borrow);
        URI savedBorrowUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBorrow.id())
                .toUri();
        return ResponseEntity.created(savedBorrowUri).body(savedBorrow);
    }

    @PutMapping("/{id}")
    ResponseEntity<BorrowDto> replaceBorrow(@PathVariable UUID id, @Valid @RequestBody BorrowDto Borrow) {
        return borrowService.replaceBorrow(id, Borrow)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBorrow(@PathVariable UUID id) {
        borrowService.deleteBorrow(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    ResponseEntity<List<BorrowDto>> searchBorrows(@RequestParam(value = "userId") UUID id) {
        List<BorrowDto> searchedBorrows = borrowService.getBorrowsByUser(id);
        if (!searchedBorrows.isEmpty()) {
            return ResponseEntity.ok(searchedBorrows);
        }
        return ResponseEntity.noContent().build();
    }
}
