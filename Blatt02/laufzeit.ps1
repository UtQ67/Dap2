$maxIter=50
for($j = 100; $j -le 1700; $j=$j+100){
    $time = 0
    for ($i = 1; $i -le $maxIter; $i++){
        $t = java Blatt02 $j insert ab
        $t = $t -as [int]
        $time = $time + $t
    }
    $time = $time / $maxIter
    echo "Arr: " $j
    echo "Average: " $time
    echo "`n"
}