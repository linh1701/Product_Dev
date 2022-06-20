<?php
    class User {
        private $id;
        private $email;
        private $password;
        private $name;
        function __construct($id, $email, $password, $name) {
            $this->id = $id;
            $this->email = $email;
            $this->password = $password;
            $this->name = $name;
        }
        public function getId() { return $this->id; }
        public function getEmail() { return $this->email; }
        public function getPassword() { return $this->password; }
        public function getName() { return $this->name; }
    }
?> 