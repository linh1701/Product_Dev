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

    public function insertProduct($name, $price){
        return $this->product_service->insertProduct($name, $price);
    }
}
?>