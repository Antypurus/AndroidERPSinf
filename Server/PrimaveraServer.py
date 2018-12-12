# standard libs
import json

# Program Libs
from utils import env_vars as env
from utils.Database import SQLServer
from utils.Hash import SHA as sha

# Flask Libs
from flask import Flask
from flask import request

app = Flask(__name__)

database = None

@app.route("/")
def hello():
    return "Welcome to the primavera zoo"

# handles vendor login procces
@app.route("/login", methods=['POST'])
def login():
    username = request.form['username']
    password = sha.SHAHash(request.form['password']).getHash()

    id = database.select_get_one("select primavera_id from salesperson where username like %s and password like %s",
                                 (username, password))
    if id is None:
        # there is no user with these credentials
        return "Error: Invalid Password Username Combo",status.HTTP_404_NOT_FOUND
    else:
        # login occurs
        ret = {}
        #build the response json
        #vendro id
        ret["primavera_id"] = id[0]
        # primavera lgin information
        ret["username"]   = env.get_env("PRIMAVERA_USERNAME")
        ret["password"]   = env.get_env("PRIMAVERA_PASSWORD")
        ret["company"]    = env.get_env("PRIMAVERA_COMPANY")
        ret["instance"]   = env.get_env("PRIMAVERA_INSTANCE")
        ret["grant_type"] = env.get_env("PRIMAVERA_GRANT")
        ret["line"]       = env.get_env("PRIMAVERA_LINE")
        return json.dumps(ret),status.HTTP_200_OK

# handles registering a vendor in the database
@app.route("/register", methods=['POST'])
def register():
    username = request.form['username']
    username_exists = database.select_get_one("select username from salesperson where username like %s;", (username,))
    if username_exists is None:
        # username has to be registered
        password = sha.SHAHash(request.form['password']).getHash()
        primavera_id = request.form["primavera_id"]

        database.insert("insert into salesperson(username,password,primavera_id) values(%s,%s,%s)",(username,password,primavera_id))
        return "Success: User Created You Can Now Login",status.HTTP_403_FORBIDDEN
    else:
        #username already exists
        return "Error: Username Already Exist",status.HTTP_200_OK


if __name__ == "__main__":
    database = SQLServer.MSSQLServer(env.get_env("DB_HOSTNAME"),
                                     env.get_env("DB_USERNAME"),
                                     env.get_env("DB_PASSWORD"),
                                     env.get_env("DB_DATABASE"))
    app.run()