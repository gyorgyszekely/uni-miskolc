var app = angular.module("myApp", []);
	app.controller("fruitCtrl", function($scope, $http) {
		
		$scope.letsDoThis = function() {
			$http.get('getFruits').then(
			function(response) {
				$scope.arr = response.data;
			},
			function(response) {
				alert("Error caused...");
			});
			$scope.tomb.push($scope.name);
		}
		$scope.tooManyElements = function() {
			return $scope.arr.length > 3;
		}
		$scope.name = "something fancy thing";
		$scope.tomb = [ "pear", "grape", "kiwi" ];
	});