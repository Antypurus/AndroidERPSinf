from colorama import init, Fore, Back, Style
init()

def print_warning(string: str):
    print(Fore.YELLOW+string+Style.RESET_ALL)