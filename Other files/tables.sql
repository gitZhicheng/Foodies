# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS Foodies;
USE Foodies;

DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS RecipeComments;
DROP TABLE IF EXISTS PostComments;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Recommendations;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS Posts;
DROP TABLE IF EXISTS Recipes;
DROP TABLE IF EXISTS CuisineTypes;
DROP TABLE IF EXISTS Administrator;
DROP TABLE IF EXISTS Experienced;
DROP TABLE IF EXISTS Novice;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
  UserId INT AUTO_INCREMENT,
  UserName VARCHAR(255),
  Password VARCHAR(255),
  FirstName VARCHAR(255),
  LastName VARCHAR(255),
  Email VARCHAR(255),
  CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId),
  CONSTRAINT uq_Users_UserName UNIQUE (UserName)
);

CREATE TABLE Novice(
  UserId INT,
  CONSTRAINT pk_Novice_UserId
    PRIMARY KEY (UserId),
  CONSTRAINT fk_Novice_UserId
    FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Experienced(
  UserId INT,
  CONSTRAINT pk_Experienced_UserId
    PRIMARY KEY (UserId),
    FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Administrator(
  UserId INT,
  CONSTRAINT pk_Administrator_UserId
    PRIMARY KEY (UserId),
  CONSTRAINT fk_Administrator_UserId
    FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE CuisineTypes(
  CuisineTypeId INT AUTO_INCREMENT,
  CuisineTypeName VARCHAR(255),
  ParentId INT,
  ParentIds VARCHAR(255),
  CONSTRAINT pk_CuisineTypes_CuisineTypeId
    PRIMARY KEY (CuisineTypeId),
  CONSTRAINT uk_CuisineTypes_CuisineTypeName
    UNIQUE KEY (CuisineTypeName)
);

CREATE TABLE Recipes(
  RecipeId INT AUTO_INCREMENT,
  PostName VARCHAR(255),
  Description LONGTEXT,
  Image LONGTEXT,
  Steps LONGTEXT,
  CookingTime INT,
  Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CuisineTypeId INT,
  Ingredientid VARCHAR(255),
  UserId INT,
  CONSTRAINT pk_Recipes_RecipeId
    PRIMARY KEY (RecipeId),
  CONSTRAINT fk_Recipes_UserId
    FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Recipes_CuisineTypeId
    FOREIGN KEY (CuisineTypeId)
    REFERENCES CuisineTypes(CuisineTypeId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Posts(
  PostId INT AUTO_INCREMENT,
  Title VARCHAR(255),
  Content LONGTEXT,
  Image LONGTEXT,
  UserId INT,
  Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  RecipeId INT,
  CONSTRAINT pk_Posts_PostId
    PRIMARY KEY (PostId),
  CONSTRAINT fk_Posts_UserId
    FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Posts_RecipeId
    FOREIGN KEY (RecipeId)
    REFERENCES Recipes(RecipeId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Ingredients(
  IngredientId INT AUTO_INCREMENT,
  IngredientName VARCHAR(255),
  CONSTRAINT pk_Ingredients_IngredientId
    PRIMARY KEY (IngredientId)
);

CREATE TABLE Recommendations(
  RecommendationId INT AUTO_INCREMENT,
  From_UserId INT,
  To_UserId INT,
  Content LONGTEXT,
  Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  RecipeId INT,
  CONSTRAINT pk_Recommendations_RecommendationId
    PRIMARY KEY (RecommendationId),
  CONSTRAINT fk_Recommendations_From_UserId
    FOREIGN KEY (From_UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Recommendations_To_UserId
    FOREIGN KEY (To_UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Recommendations_RecipeId
    FOREIGN KEY (RecipeId)
    REFERENCES Recipes(RecipeId)
    ON UPDATE CASCADE ON DELETE CASCADE
  );

CREATE TABLE Comments(
  CommentId INT AUTO_INCREMENT,
  Content LONGTEXT,
  Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  UserId INT,
  CONSTRAINT pk_Comments_CommentId
    PRIMARY KEY (CommentId),
  CONSTRAINT fk_Comments_UserId
    FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE PostComments(
  CommentId INT,
  PostId INT,
  CONSTRAINT pk_PostComments_CommentId
    PRIMARY KEY (CommentId),
  CONSTRAINT fk_PostComments_CommentId
    FOREIGN KEY (CommentId)
    REFERENCES Comments(CommentId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_PostComments_PostId
    FOREIGN KEY (PostId)
    REFERENCES Posts(PostId)
    ON UPDATE CASCADE ON DELETE CASCADE
);
  
CREATE TABLE RecipeComments(
  CommentId INT,
  RecipeId INT,
  CONSTRAINT pk_RecipeComments_CommentId
    PRIMARY KEY (CommentId),
  CONSTRAINT fk_RecipeComments_CommentId
    FOREIGN KEY (CommentId)
    REFERENCES Comments(CommentId)
    ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_RecipeComments_RecipeId
    FOREIGN KEY (RecipeId)
    REFERENCES Recipes(RecipeId)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Reviews (
  ReviewId INT AUTO_INCREMENT,
  Rating DECIMAL(2,1) NOT NULL,
  UserId INT,
  RecipeId INT,
  CONSTRAINT pk_Reviews_ReviewId PRIMARY KEY (ReviewId),
  CONSTRAINT fk_Reviews_UserId FOREIGN KEY (UserId)
    REFERENCES Users(UserId)
    ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT fk_Reviews_RecipeId FOREIGN KEY (RecipeId)
    REFERENCES Recipes(RecipeId)
    ON UPDATE CASCADE ON DELETE SET NULL
);