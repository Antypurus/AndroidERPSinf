from termcolor import cprint
def print_warning(string: str, *args):
    cprint(string, 'yellow')