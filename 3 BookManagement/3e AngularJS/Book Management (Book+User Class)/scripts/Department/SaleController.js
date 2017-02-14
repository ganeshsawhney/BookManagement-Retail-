(function() {
    var DeptApp = angular.module('DeptApp');
    DeptApp.controller('UserController', function($scope, UserService) {

        $scope.counter = UserService.counter;
        $scope.UserModel = UserService.UserModel;
        $scope.UserList = UserService.UserList;

        $scope.AddData = function(val) {
            if (val == false)
                return;
            var _User1 = {
                userId: $scope.counter + 1,
                name: $scope.UserModel.name,
                mobile: $scope.UserModel.mobile,
                admin: $scope.UserModel.admin,
            };
            $scope.counter++;
            $scope.UserList.push(_User1);
            ClearModel();
        }

        $scope.DeleteData = function(User) {
            var _index = $scope.UserList.indexOf(User);
            $scope.UserList.splice(_index, 1);
        }

        $scope.UpdateData = function(userId, EditUser) {
            if (EditUser == undefined)
                return;
            angular.forEach($scope.UserList, function(value, index) {
                if (value.userId == userId) {
                    if (EditUser.name != undefined) {
                        value.name = EditUser.name;
                        $("#" + userId + "name").text(EditUser.name);
                    }
                    if (EditUser.mobile != undefined) {
                        value.mobile = EditUser.mobile;
                        $("#" + userId + "mobile").text(EditUser.mobile);
                    }
                    if (EditUser.admin != undefined) {
                        value.admin = EditUser.admin;
                        $("#" + userId + "admin").text(EditUser.admin);
                    }
                }
            });
        }

        function ClearModel() {
            $scope.UserModel.userId = 0;
            $scope.UserModel.name = '';
            $scope.UserModel.mobile = 0;
            $scope.UserModel.admin = "";
        }
    });

}());
