package org.champqcsoft.transactionservice.presentationlayer;


import org.champqcsoft.transactionservice.buisnesslayer.PurchaseReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/purchaseReceipts")
public class PurchaseReceiptController {
    private final PurchaseReceiptService purchaseReceiptService;

    public PurchaseReceiptController(PurchaseReceiptService purchaseReceiptService){
        this.purchaseReceiptService = purchaseReceiptService;
    }
    @GetMapping()
    public ResponseEntity<List<PurchaseReceiptResponseModel>> getPurchaseReceipts(){
        return ResponseEntity.ok().body(purchaseReceiptService.getAllPurchaseReceipts());
    }
    @GetMapping("/{purchaseReceiptId}")
    public ResponseEntity<PurchaseReceiptResponseModel> getPurchaseReceiptByPurchaseReceiptId(@PathVariable String purchaseReceiptId){
        return ResponseEntity.ok().body(purchaseReceiptService.getPurchaseReceiptByPurchaseReceiptIdentifier_purchaseReceiptId(purchaseReceiptId));
    }
    @PostMapping
    public ResponseEntity<PurchaseReceiptResponseModel> createPurchaseReceipt(@RequestBody PurchaseReceiptRequestModel purchaseReceiptRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseReceiptService.createPurchaseReceipt(purchaseReceiptRequestModel));
    }
    @PutMapping("/{purchaseReceiptId}")
    public ResponseEntity<PurchaseReceiptResponseModel> updatePurchaseReceipt(@RequestBody PurchaseReceiptRequestModel purchaseReceiptRequestModel, @PathVariable String purchaseReceiptId){
        return ResponseEntity.ok().body(purchaseReceiptService.updatePurchaseReceipt(purchaseReceiptRequestModel, purchaseReceiptId));
    }
    @DeleteMapping("/{purchaseReceiptId}")
    public ResponseEntity<Void> deletePurchaseReceipt(@PathVariable String purchaseReceiptId){
        purchaseReceiptService.removePurchaseReceipt(purchaseReceiptId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
