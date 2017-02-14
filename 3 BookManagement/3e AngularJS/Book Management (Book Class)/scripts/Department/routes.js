(function() {
    var DeptApp = angular.module('DeptApp', ["ngRoute"]);
    DeptApp.config(function($routeProvider, $locationProvider) {

        $locationProvider.hashPrefix('');
        $routeProvider
            .when('/books', {
                controller: 'BookController',
                templateUrl: 'Department/BookDept.html'
            })
            .when('/users', {
                controller: 'DeptController',
                templateUrl: 'Department/UserDept.html'
            })
            .when('/sales', {
                controller: 'DeptController',
                templateUrl: 'Department/SaleDept.html'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
}());
