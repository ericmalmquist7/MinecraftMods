import os
import shutil

__location__ = os.path.realpath(os.path.join(os.getcwd(), os.path.dirname(__file__)))
os.chdir(__location__)
print("Working in directory: %s" % __location__)

target_file = None

while target_file is None:
    print("Enter filename WITH extension. This script must be in the same directory as files to clone.")
    target = input("->")

    try:
        target_file = open(target, 'r')
    except FileNotFoundError:
        print("File %s was not found." % target)

print("File %s found." % target)

keyname = target.split('.')[0] #Assume name is leftmost of the dot and is the name to replace.
extension = target.split('.')[-1] #Assume extension is rightmost of the dot and is the extension to append.

print("Enter file names to clone WITHOUT extension, seperated by commas without spaces.")

clone_string = input("->")

clone_names = clone_string.split(',')

print("Cloning to files: %s" % ("|".join(clone_names)))

print("Replacing instances of %s" % keyname)

lines = target_file.readlines()

for new_name in clone_names:
    try:
        new_file = open(new_name + "." + extension, 'w')
        
        for line in lines:
            newLine = line.replace(keyname, new_name)
            new_file.write(newLine)

        new_file.close()

    except Exception as e:
        print("New file %s failed to open." % str(new_name + "." + extension, 'w'))
        print(e.message)

target_file.close()