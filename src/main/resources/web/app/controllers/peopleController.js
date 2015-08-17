angular.module('SimpleSoftPortal', []).controller('PeopleController', function ($scope, $http) {
  
  $scope.localPeople = {"local":[
     {"firstName":"Local", "lastName":"Doe", "local":true },
     {"firstName":"Local", "lastName":"Smith", "local":true },
     {"firstName":"Local", "lastName":"Jones", "local":true }
 ]};
  
  $scope.getEmployees = function() { 
	$http.get('rest/employees').success(function(data) {
	  $scope.employees = data.employees.concat($scope.localPeople.local);
    });
  }
  

});