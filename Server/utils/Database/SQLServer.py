import pymssql  

class MSSQLServer:
    
    __connection = None
    __cursor = None

    def __init__(self, hostname: str, user: str, password: str, database: str = "master"):
        self.__connection = pymssql.connect(server=hostname, user=user, password=password, database=database)
        self.__connection.autocommit(True)

    def select_get_one(self, query:str, params: tuple):
        cursor = self.__connection.cursor()
        cursor.execute(query, params)
        return cursor.fetchone()

    def insert(self, query:str, params: tuple):
        cursor = self.__connection.cursor()
        cursor.execute(query, params)