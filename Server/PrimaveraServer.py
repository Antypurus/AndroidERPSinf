# Program Libs
from utils import env_vars as env
from utils.Database import SQLServer as db
from utils.Hash import SHA as sha

# Flask Libs
from flask import Flask
from flask import request

app = Flask(__name__)

@app.route("/")
def hello():
    return "Welcome to the primavera zoo"

@app.route('/login', methods=['POST'])
def login():
    db_con = db.MSSQLServer(env.get_env("DB_HOSTNAME"),
                            env.get_env("DB_USERNAME"),
                            env.get_env("DB_PASSWORD"),
                            env.get_env("DB_DATABASE"))
    id = db_con.select_get_one("select primavera_id from salesperson where username like %s and password like %s",
          (request.form['username'], request.form['password']))
    if id is None:
        return "ERROR"
    else:
        return id[0]

if __name__ == "__main__":
    app.run()