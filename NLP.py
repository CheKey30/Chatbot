import sys

def answer(que):
    return "answer:"+que

if __name__ == "__main__":
    #que is the question from the customer
    #you can rewirte the answer function to generate the answoer
    que = sys.argv[1]
    #print will send the answer back to user
    print(answer(que))