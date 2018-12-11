import pymssql  

class MSSQLServer:
    
    __connection = None
    __cursor = None

    def __init__(self, hostname: str, user: str, password: str, database: str = "master"):
        self.__connection = pymssql.connect(server=hostname, user=user, password=password, database=database)
        self.__cursor = self.__connection.cursor()

    def select_get_one(self, query:str, params: tuple):
        self.__cursor.execute(query, params)
        return self.__cursor.fetchone()