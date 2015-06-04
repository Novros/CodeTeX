#!/bin/bash
# Script for building CodeTeX.
echo "------------------------------------------------------------"
echo "Installing script for CodeTex"
echo "------------------------------------------------------------"

# Building procedure
echo "+ Building CodeTeX..."
mvn clean package assembly:single

if [ "$?" != "0" ]; then
	echo "ERROR: There was problem with building CodeTex."
	exit 1
fi

# Creating dir
echo "+ Creating dir..."
if [ -z "$1" ]; then
	mkdir -p CodeTeX
	TARGET="CodeTeX"
else
	mkdir -p $1
	TARGET="$1"
fi

# Copying procedure
echo "+ Copying compiled CodeTeX..."
cp target/CodeTeX-jar-with-dependencies.jar $TARGET/CodeTeX.jar
cp -r target/settings $TARGET/settings 
printf "#!/bin/bash\njava -jar CodeTeX.jar \$*\n" > $TARGET/CodeTeX

# Cleaning target
echo "+ Cleaning compiled things..."
mvn clean

echo "------------------------------------------------------------"
echo "Installing CodeTex complete!"
echo "------------------------------------------------------------"
