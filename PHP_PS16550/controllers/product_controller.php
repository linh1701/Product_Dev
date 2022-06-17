<?php

include_once $_SERVER['DOCUMENT_ROOT'].'/services/product_service.php';

class ProductController {
    private $product_service;
    public function __construct(){
        $this->product_service = new ProductService();
    }

    public function getAllProducts(){
        return $this->product_service->getAllProducts();
    }

    public function getOneProduct($id){
        return $this->product_service->getOneProduct($id);
    }

    public function insertProduct($name, $price, $quantity){
        return $this->product_service->insertProduct($name, $price, $quantity);
    }

    public function updateProduct($id, $name, $price, $quantity){
        return $this->product_service->updateProduct($id, $name, $price, $quantity);
    }

    public function deleteProduct($id){
        return $this->product_service->deleteProduct($id);
    }
}
?>