import os
from dotenv import load_dotenv

def init():
  """
  Loads the enviroment file
  """
  load_dotenv()

def get_env(var: str):
  """
  Loads an environment variable

  Args:
    var (str): Name of the environment variable

  Returns:
    The request environment variable if it is present
  """
  return os.getenv(var)

init()