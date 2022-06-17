<?php

include_once $_SERVER['DOCUMENT_ROOT'].'/database/config.php';
include_once $_SERVER['DOCUMENT_ROOT'].'/models/user.php';

class UserService {
    private $connection;

    public function __construct(){
        $this->connection = (new DatabaseConfig())->getConnection();
    }

    // sql injection
    public function register($email, $password){
        try {
            $query = "insert into tbl_users set email=:email, password=:password";

            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':password', $password);

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

    public function getUserByEmail($email){
        try {
            $query = "select id, email, password from tbl_users where email =:email";                                               
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':email', $email);            
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                extract($row);                
                return new User($id, $email, $password);
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return null;
    }

    // nhập email - tạo 1 link chứa token gửi qua email người dùng
    // kiểm tra token có hợp lệ
    // change password
    // clear token

    public function generateToken($email) {
        $token = md5($email).rand(10, 9999);
        try {
            $query = "insert into tbl_ResetPassword set email=:email, token=:token";

            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':token', $token);

            $this->connection->beginTransaction();
            if($stmt->execute()) {
                $this->connection->commit();
                return $token;
            } else {
                $this->connection->rollback();
                return null;
            }
        } catch (Exception $e) {
            echo $e;
        }
        return null;
    }

    public function getByToken($email, $token){
        try {
            $query = "select id from tbl_ResetPassword where email =:email and token =:token
                                                            and available = 1 
                                                            and createdAt > now() - interval 5 minute
                                                            limit 0, 1";                                               
            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':email', $email);            
            $stmt->bindParam(':token', $token);            
            $stmt->execute();
            if ($stmt->rowCount() > 0) {
                return true;
            }
        } catch (Exception $e) {
            echo $e->getMessage();
        }
        return false;
    }

    public function changePassword($email, $password) {
        try {
            $query = "update tbl_users set password=:password where email=:email";

            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':password', $password);

            $this->connection->beginTransaction();
            if($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollback();
                return false;
            }
        } catch (Exception $e) {
            echo $e;
        }
        return false;
    }

    public function clearToken($token) {
        try {
            $query = "update tbl_ResetPassword set available = 0 where token=:token";

            $stmt = $this->connection->prepare($query);
            $stmt->bindParam(':token', $token);

            $this->connection->beginTransaction();
            if($stmt->execute()) {
                $this->connection->commit();
                return true;
            } else {
                $this->connection->rollback();
                return false;
            }
        } catch (Exception $e) {
            echo $e;
        }
        return false;
    }
    



    
}

?>