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

    public function getOneCategories($id){
        return $this->product_service->getOneCategories($id);
    }

    public function getCategories(){
        return $this->product_service->getCategories();
    }

    public function insertCategories($name, $image){
        return $this->product_service->insertCategories($name,$image);
    }

    public function updateCategories($id, $name, $image){
        return $this->product_service->updateCategories($id, $name, $image);
    }

    public function deleteCategories($id){
        return $this->product_service->deleteCategories($id);
    }

    public function insertProduct($name, $price, $quantity, $image, $category_id){
        return $this->product_service->insertProduct($name, $price, $quantity, $image, $category_id);
    }

    public function updateProduct($id, $name, $price, $quantity, $image, $category_id){
        return $this->product_service->updateProduct($id, $name, $price, $quantity, $image, $category_id);
    }

    public function deleteProduct($id){
        return $this->product_service->deleteProduct($id);
    }
}
?>