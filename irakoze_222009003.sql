-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 10:09 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `irakoze_222009003`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateFarmerFieldView` ()   BEGIN
    -- Check if the view already exists and drop it if it does
    IF EXISTS (SELECT * FROM information_schema.TABLES WHERE TABLE_NAME = 'FarmerFieldView') THEN
        DROP VIEW FarmerFieldView;
    END IF;

    -- Create a view that displays farmer information and the total area of their fields
    CREATE VIEW FarmerFieldView AS
    SELECT
        f.farmer_id,
        f.farmer_name,
        f.farmer_location,
        (SELECT COUNT(*) FROM Field fi WHERE fi.farmer_id = f.farmer_id) AS TotalFields,
        (SELECT SUM(field_area) FROM Field fi WHERE fi.farmer_id = f.farmer_id) AS TotalFieldArea
    FROM
        Farmer f;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DisplayAllInformation` ()   BEGIN
SELECT * FROM agronomist;
SELECT * FROM crop;
SELECT * FROM farm;
SELECT * FROM field;
SELECT * FROM manager;
SELECT * FROM user;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAgronomist` (IN `p_agronomist_id` INT, IN `p_agronomist_names` VARCHAR(255), IN `p_agronomist_tel` VARCHAR(15), IN `p_location` VARCHAR(255), IN `p_degree` VARCHAR(50))   BEGIN
    INSERT INTO agronomist (
        agronomist_id,
        agronomist_names,
        agronomist_tel,
        location,
        degree
    ) VALUES (
        p_agronomist_id,
        p_agronomist_names,
        p_agronomist_tel,
        p_location,
        p_degree
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertCrop` (IN `p_crop_id` INT, IN `p_crop_type` VARCHAR(255), IN `p_crop_variety` VARCHAR(255), IN `p_field_id` INT)   BEGIN
    INSERT INTO crop (
        crop_id,
        crop_type,
        crop_variety,
        field_id
    ) VALUES (
        p_crop_id,
        p_crop_type,
        p_crop_variety,
        p_field_id
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertFarm` (IN `p_farm_name` VARCHAR(255), IN `p_location` VARCHAR(255), IN `p_farm_size` DECIMAL(10,2), IN `p_grown_crops` VARCHAR(255), IN `p_manager_id` INT)   BEGIN
    INSERT INTO farm (
        farm_name,
        location,
        farm_size,
        grown_crops,
        manager_id
    ) VALUES (
        p_farm_name,
        p_location,
        p_farm_size,
        p_grown_crops,
        p_manager_id
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertField` (IN `p_field_name` VARCHAR(255), IN `p_crop_planted` VARCHAR(255), IN `p_soil_type` VARCHAR(255), IN `p_planting_date` DATE, IN `p_harvest_date` DATE, IN `p_farmer_id` INT)   BEGIN
    INSERT INTO field (
        field_name,
        crop_planted,
        soil_type,
        planting_date,
        harvest_date,
        farmer_id
    ) VALUES (
        p_field_name,
        p_crop_planted,
        p_soil_type,
        p_planting_date,
        p_harvest_date,
        p_farmer_id
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertManager` (IN `first_name` VARCHAR(255), IN `last_name` VARCHAR(255), IN `email_adress` VARCHAR(255), IN `telephone` VARCHAR(15), IN `agronomist_id` INT)   BEGIN
    INSERT INTO manager (
        first_name,
        last_name,
        email_adress,
        telephone,
        agronomist_id
    ) VALUES (
        first_name,
        last_name,
        email_adress,
        telephone,
        agronomist_id
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertUser` (IN `p_name` VARCHAR(255), IN `p_username` VARCHAR(255), IN `p_password` VARCHAR(255), IN `p_role` VARCHAR(50))   BEGIN
    INSERT INTO user (
        name,
        username,
        password,
        role
    ) VALUES (
        p_name,
        p_username,
        p_password,
        p_role
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `RunAllSelectQueries` ()   BEGIN
    -- Query 1: Select all records from agronomist table
    SELECT * FROM agronomist;
    
    -- Query 2: Select all records from crop table
    SELECT * FROM crop;
    
    -- Query 3: Select all records from farm table
    SELECT * FROM farm;
    
    -- Query 4: Select all records from field table
    SELECT * FROM field;
    
    -- Query 5: Select all records from manager table
    SELECT * FROM manager;
    
    -- Query 6: Select all records from user table
    SELECT * FROM user;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateCropAndUser` (IN `cropID` INT, IN `newCropType` VARCHAR(255), IN `newCropVariety` VARCHAR(255), IN `newFieldID` INT, IN `userID` INT, IN `newName` VARCHAR(255), IN `newUsername` VARCHAR(255), IN `newPassword` VARCHAR(255), IN `newRole` VARCHAR(50))   BEGIN
    -- Start a transaction to ensure consistency
    START TRANSACTION;

    -- Update crop information
    UPDATE Crop
    SET
        crop_type = newCropType,
        crop_variety = newCropVariety,
        field_id = newFieldID
    WHERE crop_id = cropID;

    -- Update user information
    UPDATE User
    SET
        name = newName,
        username = newUsername,
        password = newPassword,
        role = newRole
    WHERE user_id = userID;

    -- Commit the transaction
    COMMIT;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `agronomist`
--

CREATE TABLE `agronomist` (
  `agronomist_id` int(10) NOT NULL,
  `agronomist_names` varchar(40) NOT NULL,
  `agronomist_tel` varchar(40) NOT NULL,
  `location` varchar(40) NOT NULL,
  `degree` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `agronomist`
--

INSERT INTO `agronomist` (`agronomist_id`, `agronomist_names`, `agronomist_tel`, `location`, `degree`) VALUES
(1, 'muhizi', '0987654444433', 'kigali', 'phd'),
(2, 'morren', '1234567', 'muhanga', 'bacherol'),
(3, 'IRAKOZE aurine', '123-456-7890', 'Farmville', 'PhD');

-- --------------------------------------------------------

--
-- Table structure for table `buyers`
--

CREATE TABLE `buyers` (
  `BuyerID` int(11) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `crop`
--

CREATE TABLE `crop` (
  `crop_id` int(10) NOT NULL,
  `crop_type` varchar(40) NOT NULL,
  `crop_variety` varchar(40) NOT NULL,
  `field_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `crop`
--

INSERT INTO `crop` (`crop_id`, `crop_type`, `crop_variety`, `field_id`) VALUES
(1, 'wheat', 'variety', 1),
(2, 'maize', 'growth cycle', 1),
(5, 'Wheat', 'Variety A', 1);

--
-- Triggers `crop`
--
DELIMITER $$
CREATE TRIGGER `after_crop_insert` AFTER INSERT ON `crop` FOR EACH ROW BEGIN
    INSERT INTO crop_audit (crop_id, action_type, action_timestamp)
    VALUES (NEW.crop_id, 'INSERT', NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `farm`
--

CREATE TABLE `farm` (
  `farmer_id` int(10) NOT NULL,
  `farm_name` varchar(40) NOT NULL,
  `location` varchar(40) NOT NULL,
  `farm_size` varchar(40) NOT NULL,
  `grown_crops` varchar(40) NOT NULL,
  `manager_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `farm`
--

INSERT INTO `farm` (`farmer_id`, `farm_name`, `location`, `farm_size`, `grown_crops`, `manager_id`) VALUES
(1, 'kagabo', 'kigali', '5m', 'maize,fruits,beans', 1),
(3, 'My Farm', 'Farmville', '100.50', 'Wheat, Corn', 1);

--
-- Triggers `farm`
--
DELIMITER $$
CREATE TRIGGER `AfterFarmDelete` AFTER DELETE ON `farm` FOR EACH ROW BEGIN
    -- You can include SQL statements to perform actions after delete here
    INSERT INTO AuditLog (Table_Name, Action, Timestamp)
    VALUES ('Farm', 'Delete', NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `AfterFarmUpdate` AFTER UPDATE ON `farm` FOR EACH ROW BEGIN
    INSERT INTO AuditLog (Table_Name, Action, Timestamp)
    VALUES ('Farm', 'Update', NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_farm_insert` AFTER INSERT ON `farm` FOR EACH ROW BEGIN
    INSERT INTO farm_audit (farm_id, action_type, action_timestamp)
    VALUES (farm_id, 'INSERT', NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `farmers`
--

CREATE TABLE `farmers` (
  `FarmerID` int(11) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `field`
--

CREATE TABLE `field` (
  `field_id` int(11) NOT NULL,
  `field_name` varchar(40) NOT NULL,
  `crop_planted` varchar(40) NOT NULL,
  `soil_type` varchar(40) NOT NULL,
  `planting_date` date NOT NULL,
  `harvest_date` date NOT NULL,
  `farmer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `field`
--

INSERT INTO `field` (`field_id`, `field_name`, `crop_planted`, `soil_type`, `planting_date`, `harvest_date`, `farmer_id`) VALUES
(1, 'muhoza', 'soya,banana,maize', 'clay_soil', '2342-06-10', '0000-00-00', 1);

--
-- Triggers `field`
--
DELIMITER $$
CREATE TRIGGER `AfterFieldDelete` AFTER DELETE ON `field` FOR EACH ROW BEGIN
    -- You can include SQL statements to perform actions after delete here
    INSERT INTO AuditLog (Table_Name, Action, Timestamp)
    VALUES ('Field', 'Delete', NOW());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `AfterFieldUpdate` AFTER UPDATE ON `field` FOR EACH ROW BEGIN
    -- You can include SQL statements to perform actions after update here
    INSERT INTO AuditLog (Table_Name, Action, Timestamp)
    VALUES ('Field', 'Update', NOW());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `manager_id` int(11) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `email_adress` varchar(40) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `agronomist_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`manager_id`, `first_name`, `last_name`, `email_adress`, `telephone`, `agronomist_id`) VALUES
(1, 'Irakoze', 'Claire', 'irakozemorren\"gmail.com', '087654321', 2),
(2, 'IRAKOZE', 'Chance', 'chance@example.com', '123-456-78', 1);

-- --------------------------------------------------------

--
-- Table structure for table `production`
--

CREATE TABLE `production` (
  `production_id` int(46) NOT NULL,
  `product_name` varchar(250) NOT NULL,
  `quantity` int(50) NOT NULL,
  `unit_price` int(250) NOT NULL,
  `total_price` int(50) NOT NULL,
  `production_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `production`
--

INSERT INTO `production` (`production_id`, `product_name`, `quantity`, `unit_price`, `total_price`, `production_date`) VALUES
(1, 'Beans', 30, 550, 16500, '2023-12-12');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ProductID` int(11) NOT NULL,
  `FarmerID` int(11) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Description` text DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `UploadDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `purchase_id` int(10) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `quantity` double NOT NULL,
  `unit_price` double NOT NULL,
  `total_price` double NOT NULL,
  `purchase_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`purchase_id`, `product_name`, `quantity`, `unit_price`, `total_price`, `purchase_date`) VALUES
(1, 'Beans', 30, 300, 9000, '2002-12-12');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `sales_id` int(10) NOT NULL,
  `product_name` varchar(250) NOT NULL,
  `quantity` int(250) NOT NULL,
  `unit_price` int(250) NOT NULL,
  `total_price` int(100) NOT NULL,
  `sales_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`sales_id`, `product_name`, `quantity`, `unit_price`, `total_price`, `sales_date`) VALUES
(1, 'Beans', 28, 700, 19600, '2021-12-12');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `name`, `username`, `password`) VALUES
(1, 'IRAKOZE', 'IRA', '1234'),
(2, 'HAKIZA Emmy', 'Emmy123', 'password123'),
(3, 'IRAKOZE', 'IRA', '1234'),
(4, 'ryctgvujh', 'fyycfgvujhbk', 'ctuvyjhbkj'),
(5, 'vkbj,n', 'objln', 'op;nkm,'),
(6, 'yifvjhkbm', 'ilnk', '[j\'oml'),
(7, 'yifvjhkbm', 'ilnk', '[j\'oml'),
(8, 'yifvjhkbm', 'ilnk', '[j\'oml'),
(9, 'vyihk', 'uoubj', 'in'),
(10, 'gukj', ';nl', 'cfhg'),
(222009003, 'Irakoze Maurine', 'Mauri', '12345678');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
