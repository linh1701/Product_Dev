<?php
class DatabaseConfig
{
    private $host = "10.82.74.26:3307";
    private $username = "root";
    private $password = "";
    private $database = "db_asm_network";
    public $connection;
    public function getConnection()
    {
        $this->connection = null;
        try {
            $this->connection = new PDO("mysql:host=" . $this->host .
                "; dbname=" . $this->database, $this->username, $this->password);
            $this->connection->exec("set names utf8");
        } catch (Exception $e) {
            echo "Kết nối không thành công: " . $e->getMessage();
        }
        return $this->connection;
    }
}