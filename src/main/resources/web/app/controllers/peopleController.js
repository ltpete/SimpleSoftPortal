angular.module('SimpleSoftPortal', []).controller('PeopleController', function ($scope, $http, $log) {
  
	$scope.employees = [
		{"firstName":"Local", "lastName":"Doe", "local":true },
		{"firstName":"Local", "lastName":"Smith", "local":true },
		{"firstName":"Local", "lastName":"Jones", "local":true }
	];
  
	$scope.getEmployees = function() { 
    $http.get('rest/employees').then(
      function(response) {
        $scope.employees = response.data;
      }
    );
  }
	
  $scope.delete = function(employee) { 
    $http.delete('rest/employees/' + employee.id).then(
      function() {
        $scope.employees.splice($scope.employees.indexOf(employee), 1);
      }
    );
  }
  
  $scope.postEmployees = function() { 
  	var dataObj = {
      firstName : $scope.firstName, 
      lastName : $scope.lastName
  	};
		$http.post('rest/employees', dataObj).then(
		  function(data) {
		    $scope.firstName = ""
		    $scope.lastName = ""
		    $scope.getEmployees()
      },
      function(error) {
		    $scope.firstName = ""
		    $scope.lastName = error
      }
    );
  }

});