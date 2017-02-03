var app = angular.module('NablibApp',['ui.router','ngStorage', 'NatLib.BookModule', 'NatLib.UserModule']);
var bookModule = angular.module('NatLib.BookModule',['ui.router','ngStorage']);
var userModule = angular.module('NatLib.UserModule',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/SpringBootLibraryApp',
    USER_SERVICE_API : 'http://localhost:8080/SpringBootLibraryApp/api/user/',
    BOOK_SERVICE_API : 'http://localhost:8080/SpringBootLibraryApp/api/book/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/person',
                controller:'UserController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, UserService) {
                        console.log('Load all users');
                        var deferred = $q.defer();
                        UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
            .state('bks', {
            	url: '/',
            	templateUrl: 'partials/book',
            	controller:'BookController',
            	controllerAs:'bookCtrl',
            	resolve: {
            		books: function ($q, BookService) {
            			console.log('Load all books');
            			var bookdeferred = $q.defer();
            			BookService.loadAllBooks().then(bookdeferred.resolve, bookdeferred.resolve);
            			return bookdeferred.promise;
            		}
            	}
            });
        $urlRouterProvider.otherwise('/');
    }]);

