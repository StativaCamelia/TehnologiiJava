from matplotlib import pyplot as plt
import ast

def plot(path, out):
    my_file = open(path, "r")
    content = ast.literal_eval(my_file.read())
    plt.plot(content)
    plt.savefig(out)

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    plot("../ServerCaller/src/resultSync.txt", "sync")
    plot("../ServerCaller/src/resultAsync.txt", "result")
