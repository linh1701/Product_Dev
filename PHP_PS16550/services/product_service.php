<?php

include_once $_SERVER['DOCUMENT_ROOT'].'/database/config.php';

class ProductService {
    private $connection;
    private $tblProducts = "tbl_products";
    /**
     * đây là constructor trong php
    */
    public function __construct(){
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllProducts(){
        try {
            $query = "select id, name, price, quantity "
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
                        "quantity" => $quantity
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
            $query = "select id, name, price, quantity from "
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
                );                
                return $product;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return null;
    }

   
    public function insertProduct( $name, $price, $quantity){
        try {
            $query = "insert into " . $this->tblProducts . 
            " (name, price, quantity, image, category_id) 
                values (:name, :price, :quantity, :image, :category_id)";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':price', $price);
            $stmt->bindParam(':quantity', $quantity);
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

    public function updateProduct($id,$name, $price, $quantity){
        try {
            $query = "update " . $this->tblProducts .
            " set name=:name, price=:price, quantity=:quantity, 
            image=:image, category_id=:category_id where id=:id";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':id', $id);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':price', $price);
            $stmt->bindParam(':quantity', $quantity);
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