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
	self.methods = {
     availableOptions: [
       {id: 'GET', name: 'GET'},
       {id: 'POST', name: 'POST'},
       {id: 'PUT', name: 'PUT'},
	   {id: 'DELETE', name: 'DELETE'}
     ],
     selectedOption: {id: 'GET', name: 'GET'} //This sets the default value of the select in the ui
     };
	self.url = "/product-catalogue/catalogue/";
	self.showjson = false;
	self.onMethodChange = function() {
		var selMtd = self.methods.selectedOption.id;
		if (selMtd)  {
			switch (selMtd) {
				case 'POST': 
					self.showjson = true; 
					break;
				case 'PUT':
					self.showjson = true; 
					break;
				default : 
					self.showjson = false; 
					break;
			}
		}
	}
	self.submitToURI = function() {
		var selMtd = self.methods.selectedOption.id;
		console.log(selMtd + ' ' + self.url + ' ' +self.json);
		try { 
			if (selMtd && self.url)  {
				switch (selMtd) {
					case 'POST': 
						console.log('POST');
						if(self.json) {
							try{
								var obj = {};
								try{
									obj = JSON.parse(self.json);
									self.json = JSON.stringify(obj, undefined, 4);									
								} catch (e) {
									alert('Invalid json ' + self.json);
								}
								$http.post(self.url, obj).then(function (response)  {
									self.status = 'Response status : ' + response.statusText + ' code : ' + response.status;
									try{
										self.response = JSON.stringify(response.data, undefined, 4);
									} catch (e) {
										self.response = response.data;
									}
									try{
										self.headers = JSON.stringify(response.headers(), undefined, 4);
									} catch (e) {
										self.headers = response.headers();
									}
									try{
										self.config = JSON.stringify(response.config, undefined, 4);
									} catch (e) {
										self.config = response.config;
									}
								});
							} catch(e) {
								console.log(e);
							}
						}
						break;
					case 'PUT':
						console.log('PUT');
						if(self.json) {
							try{
								var obj = {};
								try{
									obj = JSON.parse(self.json);
									self.json = JSON.stringify(obj, undefined, 4);									
								} catch (e) {
									alert('Invalid json ' + self.json);
								}
								$http.put(self.url, obj).then(function (response)  {
									self.status = 'Response status : ' + response.statusText + ' code : ' + response.status;
									try{
										self.response = JSON.stringify(response.data, undefined, 4);
									} catch (e) {
										self.response = response.data;
									}
									try{
										self.headers = JSON.stringify(response.headers(), undefined, 4);
									} catch (e) {
										self.headers = response.headers();
									}
									try{
										self.config = JSON.stringify(response.config, undefined, 4);
									} catch (e) {
										self.config = response.config;
									}
								});
							} catch(e) {
								console.log(e);
							}
						}
						break;
					case 'DELETE':
					    console.log('DELETE');
						
							try{
								$http.delete(self.url).then(function (response)  {
									self.status = 'Response status : ' + response.statusText + ' code : ' + response.status;
									try{
										self.response = JSON.stringify(response.data, undefined, 4);
									} catch (e) {
										self.response = response.data;
									}
									try{
										self.headers = JSON.stringify(response.headers(), undefined, 4);
									} catch (e) {
										self.headers = response.headers();
									}
									try{
										self.config = JSON.stringify(response.config, undefined, 4);
									} catch (e) {
										self.config = config;
									}
								});
							} catch(e) {
								console.log(e);
							}
						
						break;
					default :
						console.log('GET');
							try{
								$http.get(self.url).then(function (response)  {
									self.status = 'Response status : ' + response.statusText + ' code : ' + response.status;
									try{
										self.response = JSON.stringify(response.data, undefined, 4);
									} catch (e) {
										self.response = response.data;
									}
									try{
										self.headers = JSON.stringify(response.headers(), undefined, 4);
									} catch (e) {
										self.headers = response.headers();
									}
									try{
										self.config = JSON.stringify(response.config, undefined, 4);
									} catch (e) {
										self.config = response.config;
									}
								});
							} catch(e) {
								console.log(e);
							}
						
						break;
				}
			}
		} catch(e){
			console.log(e);
		}
	};
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




