#!/usr/bin/env python
# -*- coding: utf-8 -*-

from flask import Flask
from flask import jsonify
from flask_sqlalchemy import SQLAlchemy
from config import config

def create_app(enviroment):
    app = Flask(__name__)
    app.config.from_object(enviroment)
    return app

enviroment = config['development']
app = create_app(enviroment)
db = SQLAlchemy(app)

def conectar():
    import mysql.connector

    config_mysql = {
        'user': 'pfcapi',
        'password': 'tRypp=d"H^28ui-4~7Lj',
        'host': 'localhost',
        'database': 'pfc_appuscf',
    }
    conector = mysql.connector.connect(**config_mysql)
    cursor = conector.cursor()
    return cursor

@app.route('/v2/plantilla', methods=['GET'])
def get_users():
    cursor = conectar()
    query = ("SELECT * FROM plantilla")
    cursor.execute(query)
    jugadores = []
    for jugador in cursor.fetchall():
        jugadores.append({
            'numero': jugador[0],
            'nombre': jugador[1],
            'apellidos': jugador[2],
            'posicion': jugador[3],
            'img': jugador[4]
        })
    cursor.close()
    conector.close()

    response = {
        'estado': 200,
        'jugadores': jugadores
        }
    return jsonify(response)

@app.route('/', methods=['GET'])
def index():
	return 0

@app.route('/v2/', methods=['GET'])
def main():
	return 0

if __name__ == '__main__':
    app.run(host='0.0.0.0',debug=True)