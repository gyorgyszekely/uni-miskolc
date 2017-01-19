angular.module('userOrigination').controller('regController', function($scope, $http, $mdToast, $mdDialog, pageService) {
	
	var vm = this;
	
	vm.register = function() {
		
		function checkColorEnabled(colorList) {
			var enabledColors = [];
			angular.forEach(colorList, function(value, key) {
				if(value.enabled === true) {
					enabledColors.push(value.colorCode);
					value.enabled = false;
				}
			});
			return enabledColors;
		}
		
		var newUserRequest = {
			userName : vm.user.username,
			creditBalance : vm.user.credit,
			qualification : vm.user.school,
			gender : vm.user.gend,
			favouriteColor : checkColorEnabled(vm.favoriteColorType)
		}
		
		var success = function(response) {
			
			if (response.data.success) {
				pageService.showToastFn('Registration success!');
				pageService.getRegisteredUsersFn().then(function(data) {
					vm.registeredUsers = data;
				});
				delete vm.user;
				
			} else {
				pageService.showToastFn('Provided data is invalid, registration failed!');
			}
		}
		
		var failed = function() {
			pageService.showToastFn('Registration failed!');
		}
		
		pageService.registerUserFn(
				newUserRequest,
				success, 
				failed
		);
		
	}
	
	vm.deleteuser = function(userId) {
		
		var dialog = $mdDialog.confirm()
			.title("Delete confirmation")
			.textContent("Are you sure to delete user?")
			.ok("Yes")
			.cancel("No");
		
		$mdDialog.show(dialog).then(function() {
			var success = function() {
				pageService.getRegisteredUsersFn().then(function(data) {
					vm.registeredUsers = data;
				});
				pageService.showToastFn("User deleted!");
			}
			
			var failed = function() {
				pageService.showToastFn("Delete failed! :'(");
			}
			
			pageService.deleteUserFn(userId, success, failed);
		}, function() {
			console.debug("User deletion has been rejected.");
		});
	}
	
	vm.preload = function() {
		pageService.getPreloadFn().then(function(data) {
			vm.favoriteColorType = data.favoriteColorType;
			vm.qualificationType = data.qualificationType;
		});
	}
	
	vm.logout = function() {
		$http.post('logout',{}).then(function(response) {
			console.debug("Succesfully logged out.");
		}, function(response) {
			console.debug("Error.");
		});
		
	};
	
	vm.preload();
	
});