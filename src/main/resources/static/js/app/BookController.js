'use strict';
bookModule.controller('BookController',
    ['BookService', '$scope',  function( BookService, $scope) {

        var self = this;
        self.book = {};
        self.books=[];

        self.submit = submit;
        self.getAllBooks = getAllBooks;
        self.createBook = createBook;
        self.updateBook = updateBook;
        self.removeBook = removeBook;
        self.editBook = editBook;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        function submit() {
            console.log('Submitting');
            if (self.book.id === undefined || self.book.id === null) {
                console.log('Saving New Book', self.book);
                createBook(self.book);
            } else {
                updateBook(self.book, self.book.id);
                console.log('Book updated with id ', self.book.id);
            }
        }

        function createBook(book) {
            console.log('About to create book');
            BookService.createBook(book)
                .then(
                    function (response) {
                        console.log('Book created successfully');
                        self.successMessage = 'Book created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.book={};
                        $scope.bookForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Book');
                        self.errorMessage = 'Error while creating Book: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateBook(book, id){
            console.log('About to update book');
            BookService.updateBook(book, id)
                .then(
                    function (response){
                        console.log('Book updated successfully');
                        self.successMessage='Book updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.bookForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Book');
                        self.errorMessage='Error while updating Book '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeBook(id){
            console.log('About to remove Book with id '+id);
            BookService.removeBook(id)
                .then(
                    function(){
                        console.log('Book '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing book '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllBooks(){
            return BookService.getAllBooks();
        }

        function editBook(id) {
            self.successMessage='';
            self.errorMessage='';
            BookService.getBook(id).then(
                function (book) {
                    self.book = book;
                },
                function (errResponse) {
                    console.error('Error while removing book ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.book={};
            $scope.bookForm.$setPristine(); //reset Form
        }
    }


    ]);