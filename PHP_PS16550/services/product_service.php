<?php

include_once $_SERVER['DOCUMENT_ROOT'].'/database/config.php';

class ProductService {
    private $connection;
    private $tblProducts = "tbl_sanpham";
    /**
     * đây là constructor trong php
    */
    public function __construct(){
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    public function getAllProducts(){
        try {
            $query = "select id, name, price from "
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
                        "price" => $price
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
   
    public function insertProduct( $name, $price){
        try {
            $query = "insert into " . $this->tblProducts . 
            " (name, price) 
                values (:name, :price)";
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':price', $price);
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

}

?>