import hashlib

class SHAHash:

    __hash = None
    
    def __init__(self, msg:str):
        self.__hash = hashlib.sha512()
        self.__hash.update(msg.encode("UTF-8"))

    def getHash(self):
        return self.__hash.hexdigest()

    def getDigestSize(self):
        return self.__hash.digest_size

    def getBlockSize(self):
        return self.__hash.block_size
