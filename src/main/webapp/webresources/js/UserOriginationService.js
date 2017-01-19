angular.module('userOrigination').service('pageService', function($http, $mdToast) {
	
	return {
		getPreloadFn : getPreload,
		getRegisteredUsersFn : getRegisteredUsers,
		registerUserFn : registerUser,
		deleteUserFn : deleteUser,
		showToastFn : showToast
	}
	
	function getPreload() {
		return $http.get('preload').then(handlePreloadData);
		
		function handlePreloadData(preloadResult) {
			return preloadResult.data;
		}
	}
	
	function getRegisteredUsers() {
		return $http.get('view').then(handlePreloadData);
		
		function handlePreloadData(preloadResult) {
			return preloadResult.data;
		}
	}
	
	function registerUser(userData, successCallback, errorCallback) {
		$http.post('manage', userData)
		.then(successCallback, errorCallback);
	}

	function deleteUser(userId, successCallback, errorCallback) {
		$http.post('delete', userId)
			.then(successCallback, errorCallback);
	}
	
	function showToast(message) {
		 $mdToast.show($mdToast.simple().textContent(message).hideDelay(3000));
	}
	
});