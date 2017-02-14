(function() {
    var DeptApp = angular.module('DeptApp');
    DeptApp.controller('BookController', function($scope, BookService) {

        $scope.counter = BookService.counter;
        $scope.BookModel = BookService.BookModel;
        $scope.BookList = BookService.BookList;

        $scope.AddData = function(val) {
            if (val == false)
                return;
            var _Book1 = {
                isbn: $scope.counter + 1,
                name: $scope.BookModel.name,
                authorId: $scope.BookModel.authorId,
                edition: $scope.BookModel.edition,
                price: $scope.BookModel.price,
                availableUnits: $scope.BookModel.availableUnits,
            };
            $scope.counter++;
            $scope.BookList.push(_Book1);
            ClearModel();
        }

        $scope.DeleteData = function(Book) {
            var _index = $scope.BookList.indexOf(Book);
            $scope.BookList.splice(_index, 1);
        }

        $scope.UpdateData = function(isbn, EditBook) {
            if (EditBook == undefined)
                return;
            angular.forEach($scope.BookList, function(value, index) {
                if (value.isbn == isbn) {
                    if (EditBook.name != undefined) {
                        value.name = EditBook.name;
                        $("#" + isbn + "name").text(EditBook.name);
                    }
                    if (EditBook.authorId != undefined) {
                        value.authorId = EditBook.authorId;
                        $("#" + isbn + "authorId").text(EditBook.authorId);
                    }
                    if (EditBook.edition != undefined) {
                        value.edition = EditBook.edition;
                        $("#" + isbn + "edition").text(EditBook.edition);
                    }
                    if (EditBook.price != undefined) {
                        value.price = EditBook.price;
                        $("#" + isbn + "price").text(EditBook.price);
                    }
                    if (EditBook.availableUnits != undefined) {
                        value.availableUnits = EditBook.availableUnits;
                        $("#" + isbn + "availableUnits").text(EditBook.availableUnits);
                    }
                }
            });
        }

        function ClearModel() {
            $scope.BookModel.isbn = 0;
            $scope.BookModel.name = '';
            $scope.BookModel.authorId = 0;
            $scope.BookModel.edition = "";
            $scope.BookModel.price = 0;
            $scope.BookModel.availableUnits = 0;
        }
    });

}());
