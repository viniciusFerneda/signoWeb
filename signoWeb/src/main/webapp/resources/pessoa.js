/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module("SignoApp", [])
        .value('urlBase', 'http://localhost:9080/signoWeb/rest/')
        .controller("PessoaController", function ($http, urlBase) {
            var self = this;
            self.usuario = 'Vinícius Ferneda de Lima';

            self.pessoas = [];
            self.pessoa = undefined;

            self.novo = function () {
                self.pessoa = {};
            };

            self.salvar = function () {
                var metodo = 'POST';
                if (self.pessoa.codigo) {
                    metodo = 'PUT';
                }

                $http({
                    method: metodo,
                    url: urlBase + 'pessoas/',
                    data: self.pessoa
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                	console.log(response);
                    self.ocorreuErro();
                });
            };

            self.alterar = function (pessoa) {
                self.pessoa = pessoa;
            };

            self.deletar = function (pessoa) {
                self.pessoa = pessoa;

                $http({
                    method: 'DELETE',
                    url: urlBase + 'pessoas/' + self.pessoa.codigo + '/'
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };

            self.concluir = function (pessoa) {
                self.pessoa = pessoa;

                $http({
                    method: 'PUT',
                    url: urlBase + 'pessoas/' + self.pessoa.id + '/'
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };

            self.ocorreuErro = function () {
                alert("Ocorreu um erro inesperado!");
            };

            self.atualizarTabela = function () {
                $http({
                    method: 'GET',
                    url: urlBase + 'pessoas/'
                }).then(function successCallback(response) {
                    self.pessoas = response.data;
                    self.pessoa = undefined;
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };

            self.activate = function () {
                self.atualizarTabela();
            };
            self.activate();
        });