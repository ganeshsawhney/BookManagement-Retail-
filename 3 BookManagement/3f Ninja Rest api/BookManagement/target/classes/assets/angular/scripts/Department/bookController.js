var DeptApp = angular.module('DeptApp');
DeptApp.controller('BookController', function($scope, $http, BookService) {

	BookService.then(function(data) {
		$scope.counter = BookService.counter;
		$scope.BookModel = BookService.BookModel;
		$scope.BookList = data.data;
	});

	$scope.AddData = function(val) {

		if (val == false)
			return;
		var _Book1 = ({
			"isbn" : $scope.BookModel.isbn,
			"name" : $scope.BookModel.name,
			"authorId" : $scope.BookModel.authorId,
			"edition" : $scope.BookModel.edition,
			"price" : $scope.BookModel.price,
			"availableUnits" : $scope.BookModel.availableUnits,
		});

		$.ajax({
			type : "POST",
			url : "/book",
			data : JSON.stringify(_Book1),
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(msg) {
				$scope.BookList.push(_Book1);
			},
			error : function(errormessage) {
				alert(errormessage.statusText);
				// do something else

			}
		});
		ClearModel();
		 return $http.get("/book").then(function (data) {
			 $scope.BookList = data.data;
	        	console.log("success");
	    		ClearModel();
		 });
 		ClearModel();
	}
	
	$scope.DelData = function(val,isbn) {
		if (val == false)
			return;

		var _Book1 = ({
			"isbn" : isbn,
			"name" : "a",
			"authorId" : 1,
			"edition" : "0",
			"price" : 1,
			"availableUnits" : 1,
		});
		$.ajax({
			type : "DELETE",
			url : "/book",
			data : JSON.stringify(_Book1),
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			error : function(errormessage) {
				alert(errormessage.statusText);
				// do something else

			}
		});
 		ClearModel();
		 return $http.get("/book").then(function (data) {
			 $scope.BookList = data.data;
	        	console.log("success");
	     		ClearModel();
		 });
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
					$("#" + isbn + "availableUnits").text(
							EditBook.availableUnits);
				}
			}
		});
		EditBook.isbn=isbn;
		alert(JSON.stringify(EditBook));
		$.ajax({
			type : "PUT",
			url : "/book",
			data : JSON.stringify(EditBook),
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			error : function(errormessage) {
				alert(errormessage.statusText);
				// do something else

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
