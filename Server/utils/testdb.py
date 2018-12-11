from utils import env_vars as env
from utils.Database import SQLServer as db

if __name__ == "__main__":
    db_con = db.MSSQLServer(env.get_env("DB_HOSTNAME"),
                            env.get_env("DB_USERNAME"),
                            env.get_env("DB_PASSWORD"),
                            env.get_env("DB_DATABASE"))
    res = db_con.select_get_one("select * from salesperson;", ())
    print(res[1])