#!/bin/sh
for f in $(find . -type f -name \*.txt); do
  cp $f $f.new1
  echo "applying changes $f"
  sed "s/\([a-zA-Z!?\.]\)--/\1 --/g" $f.new1 > $f.new2
  sed "s/--\([a-zA-Z!?\.]\)/-- \1/g" $f.new2 > $f.new3

  mv $f.new3 $f
  rm $f.new{1,2}
done
