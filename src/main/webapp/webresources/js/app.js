/**
 * Simple Angular JS based REST Client for User application. 
 */

var app = angular.module('userOrigination', [ 'ngMessages', 'ngMaterial' ]);

app.service('$pageService', function($http, $mdToast) {
	
	this.getPreload = function() {
		return $http.post('/adminapi/preload', userData).then(handlePreloadData);
		
		function handlePreloadData(preloadResult) {
			return preloadResult.data;
		}
	}
	
	this.getRegisteredUsers = function() {
		return $http.get('/adminapi/view').then(handlePreloadData);
		
		function handlePreloadData(preloadResult) {
			return preloadResult.data;
		}
	}
	
	this.registerUser = function(userData, successCallback, errorCallback) {
		$http.post('/adminapi/manage', userData)
		.then(successCallback, errorCallback);
	}

	this.deleteUser = function(userId, successCallback, errorCallback) {
		$http.post('/adminapi/delete', userId)
			.then(successCallback, errorCallback);
	}
	
	this.showToast = function(message) {
		 $mdToast.show($mdToast.simple().textContent(message).hideDelay(3000));
	}
	
});


app.controller("regController", function($scope, $http, $mdToast, $mdDialog, $pageService) {
	
	$scope.register = function() {
		
		var success = function(response) {
			
			if (response.data.isSuccess) {
				$pageService.showToast("Registration success!");
			} else {
				$pageService.showToast("Provided data is invalid, registration failed!");
			}
		}
		
		var failed = function() {
			$pageService.showToast("Registration failed!");
		}
		
		$pageService.registerUser(
				{
					userName : '',
					creditBalance : '',
					qualification : '',
					gender : '',
					favouriteColor : []
				},
				success, 
				failed
		);
		
	}
	
});