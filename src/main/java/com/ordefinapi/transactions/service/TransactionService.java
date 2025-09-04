package com.ordefinapi.transactions.service;

import com.ordefinapi.transactions.dto.TransactionRequestDTO;
import com.ordefinapi.transactions.dto.TransactionResponseDTO;
import com.ordefinapi.transactions.model.Category;
import com.ordefinapi.transactions.model.Transaction;
import com.ordefinapi.transactions.repository.CategoryRepository;
import com.ordefinapi.transactions.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO dto, UUID userId) {
        Category category = categoryRepository.getReferenceById(dto.getCategoryId());

        Transaction tx = new Transaction();
        tx.setAmount(dto.getAmount());
        tx.setDate(dto.getDate());
        tx.setType(Transaction.Type.valueOf(dto.getType()));
        tx.setCategory(category);
        tx.setDescription(dto.getDescription());

        Transaction saved = transactionRepository.save(tx);

        return mapToDTO(saved);
    }

    public List<TransactionResponseDTO> getTransactionsByUser(UUID userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        return transactions.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private TransactionResponseDTO mapToDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setDate(transaction.getDate());
        dto.setType(transaction.getType().name());
        dto.setCategoryName(transaction.getCategory().getName());
        dto.setDescription(transaction.getDescription());
        return dto;
    }
}
