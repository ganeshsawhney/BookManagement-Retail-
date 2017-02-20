(function() {
    var DeptApp = angular.module('DeptApp');

    DeptApp.service('BookService', function($http) {
    	var promise = $http.get("/book").success(function (data) {
        	this.BookList = [];
        	this.BookList = data;
            this.counter = 0;
            this.BookModel = {
                isbn: 0,
                name: "",
                authorId: 0,
                edition: "",
                price: 0,
                availableUnits: 0,
            };
        	console.log(this.BookList);
        	return data;
        });
//    	then(function(response) {
//        	this.BookList = [];
//        	this.BookList = response.data;
//            this.counter = 0;
//            this.BookModel = {
//                isbn: 0,
//                bkname: "",
//                authorid: 0,
//                edition: "",
//                price: 0,
//                available_units: 0,
//            };
//        	console.log(this.BookList);
//        });
        return promise;
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
