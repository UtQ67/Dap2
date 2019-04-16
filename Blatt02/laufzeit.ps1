$maxIter=70
for($j = 1000; $j -le 17000; $j=$j+1000){
    $time = 0
    for ($i = 1; $i -le $maxIter; $i++){
        $t = java Blatt02 $j merge ab
        $t = $t -as [int]
        $time = $time + $t
    }
    $time = $time / $maxIter
    echo "Arr: " $j
    echo "Average: " $time
    echo "`n"
}