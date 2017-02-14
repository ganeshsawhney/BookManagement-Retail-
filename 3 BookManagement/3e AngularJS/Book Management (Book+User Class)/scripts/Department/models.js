(function() {
    var DeptApp = angular.module('DeptApp');

    DeptApp.service('BookService', function() {
        this.counter = 0;
        this.BookModel = {
            isbn: 0,
            name: "",
            authorId: 0,
            edition: "",
            price: 0,
            availableUnits: 0,
        };
        this.BookList = [];
    });

    DeptApp.service('UserService', function() {
        this.counter = 0;
        this.UserModel = {
            isbn: 0,
            name: "",
            authorId: 0,
            edition: "",
            price: 0,
            availableUnits: 0,
        };
        this.UserList = [];
    });
}());
