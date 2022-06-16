<?php

include_once $_SERVER['DOCUMENT_ROOT'].'/database/config.php';

class ProductService {
    private $connection;
    private $tblProducts = "tbl_products";
    private $tblCategories = "tbl_categories";
    /**
     * đây là constructor trong php
    */
    public function __construct(){
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllProducts(){
        try {
            $query = "select id, name, price, quantity, image, category_id from "
                                                . $this->tblProducts;
            $stmt = $this->connection->prepare($query);
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $data = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                    extract($row);
                    $product = array(
                        "id" => $id,
                        "name" => $name,
                        "price" => $price,
                        "quantity" => $quantity,
                        "image" => $image,
                        "category_id" => $category_id,
                    );
                    array_push($data, $product);
                }
                return $data;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return null;
    }

    public function getOneProduct($id){
        try {
            $query = "select id, name, price, quantity, image, category_id from "
                                                . $this->tblProducts . " where id=:id";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':id', $id); 
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                extract($row);
                $product = array(
                    "id" => $id,
                    "name" => $name,
                    "price" => $price,
                    "quantity" => $quantity,
                    "image" => $image,
                    "category_id" => $category_id,
                );                
                return $product;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return null;
    }

    public function getCategories(){
        try {
            $query = "select id, name, image from tbl_categories";
            $stmt = $this->connection->prepare($query);
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $data = array();
                while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
                    extract($row);
                    $item = array(
                        "id" => $id,
                        "name" => $name,
                        "image" => $image
                    );
                    array_push($data, $item);
                }
                return $data;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return null;
    }

    public function getOneCategories($id){
        try {
            $query = "select id, name, image from "
                                                . $this->tblCategories . " where id=:id";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':id', $id); 
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                extract($row);
                $category = array(
                    "id" => $id,
                    "name" => $name,
                    "image" => $image,
                );                
                return $category;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return null;
    }

    public function insertCategories( $name, $image){
        try {
            $query = "insert into " . $this->tblCategories . 
            " (name,image) values (:name, :image)";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':image', $image);
            $this->connection->beginTransaction();
            if($stmt->execute()){
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }            
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return false;

    }

    public function updateCategories($id,$name, $image){
        try {
            $query = "update " . $this->tblCategories .
            " set name=:name, image=:image where id=:id";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':id', $id);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':image', $image);
            $this->connection->beginTransaction();
            if($stmt->execute()){
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }            
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return false;
    }

    public function deleteCategories($id){
        try {
            $query = "delete from " . $this->tblCategories. " where id=:id"; 
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':id', $id);
            $stmt->execute();
            $this->connection->beginTransaction();
            if($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollback();
                return false;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return false;
    }

    public function insertProduct( $name, $price, $quantity, $image, $category_id){
        try {
            $query = "insert into " . $this->tblProducts . 
            " (name, price, quantity, image, category_id) 
                values (:name, :price, :quantity, :image, :category_id)";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':price', $price);
            $stmt->bindParam(':quantity', $quantity);
            $stmt->bindParam(':image', $image);
            $stmt->bindParam(':category_id', $category_id);
            $this->connection->beginTransaction();
            if($stmt->execute()){
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }            
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return false;

    }

    public function updateProduct($id,$name, $price, $quantity, $image, $category_id){
        try {
            $query = "update " . $this->tblProducts .
            " set name=:name, price=:price, quantity=:quantity, 
            image=:image, category_id=:category_id where id=:id";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':id', $id);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':price', $price);
            $stmt->bindParam(':quantity', $quantity);
            $stmt->bindParam(':image', $image);
            $stmt->bindParam(':category_id', $category_id);
            $this->connection->beginTransaction();
            if($stmt->execute()){
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollBack();
                return false;
            }            
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return false;
    }

    public function deleteProduct($id){
        try {
            $query = "delete from " . $this->tblProducts. " where id=:id"; 
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':id', $id);
            $stmt->execute();
            $this->connection->beginTransaction();
            if($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollback();
                return false;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return false;
    }
}

?>