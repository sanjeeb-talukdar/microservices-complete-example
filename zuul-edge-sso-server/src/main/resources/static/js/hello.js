angular.module('hello', [ 'ngRoute', 'ngSanitize', 'ngPrettyJson' ]).config(function($routeProvider, $httpProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home',
		controllerAs : 'controller'
	}).otherwise('/');
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}).controller('navigation', function($rootScope, $http, $location, $route) {
	var self = this;
	self.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};
	$http.get('auth/user').then(function(response) {
		if (response.data.name) {
			$rootScope.authenticated = true;
		} else {
			$rootScope.authenticated = false;
		}
	}, function() {
		$rootScope.authenticated = false;
	});
	self.credentials = {};
	self.logout = function() {
		$http.post('logout', {}).finally(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		});
	}
}).controller('home', function($http) {
	var self = this;	
	self.getUser = function() {
		$http.get('auth/user').then(function(response) {
			self.user = JSON.stringify(response.data, undefined, 4);
		});
	};
	self.getAccounts = function() {
		$http.get('account-details/accounts').then(function(response) {
			self.accounts = JSON.stringify(response.data, undefined, 4);
		});
	};
	self.getAccountDetails = function() {
		$http.get('account-details/accounts/1').then(function(response) {
			self.accountDetails = JSON.stringify(response.data, undefined, 4);
		});
	};
	self.getAccountTransactions = function() {
		$http.get('account-transactions/accounts/1/transactions').then(function(response) {
			self.accountTransactions = JSON.stringify(response.data, undefined, 4);
		});
	};
	self.getAccountTransactionDetails = function() {
		$http.get('account-transactions/accounts/1/transactions/1').then(function(response) {
			self.accountTransactionDetails = JSON.stringify(response.data, undefined, 4);
		});
	};
	self.getCatalogues = function() {
		$http.get('product-catalogue/catalogue/').then(function(response) {
			self.catalogue = JSON.stringify(response.data, undefined, 4);
		});
	};
	self.getProducts = function() {
		$http.get('product-catalogue/product/').then(function(response) {
			self.products = JSON.stringify(response.data, undefined, 4);
		});
	};
});

var app1 = angular.module('test', [ 'ngRoute', 'ngSanitize', 'ngPrettyJson' ]);




