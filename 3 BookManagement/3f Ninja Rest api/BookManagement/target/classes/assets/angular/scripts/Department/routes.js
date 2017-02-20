(function() {
    var DeptApp = angular.module('DeptApp', ["ngRoute"]);
    DeptApp.config(function($routeProvider, $locationProvider) {

        $locationProvider.hashPrefix('');
        $routeProvider
            .when('/books', {
                controller: 'BookController',
                templateUrl: '/assets/angular/Department/BookDept.html'
            })
            .when('/users', {
                controller: 'UserController',
                templateUrl: '/assets/angular/Department/UserDept.html'
            })
            .when('/sales', {
                controller: 'DeptController',
                templateUrl: '/assets/angular/Department/SaleDept.html'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
}());
