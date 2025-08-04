python3 generate_resources.py
echo "Current path is $PWD"
rm -r ../resources/*
python3 merge_resources.py
python3 copy_resources.py
