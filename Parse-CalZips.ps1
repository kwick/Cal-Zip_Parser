#Create hash table for each counties mail distrobution center

$CityHT = @{}
900,901,902,903,904,905, 907, 908 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\LA.txt")}
906, 917, 918 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\Industry.txt")}
910,916 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\SantaClarita.txt")}
919,921 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\SanDiego.txt")}
922..925 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\SanBernardino.txt")}
926,927 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\SantaAna.txt")}
928 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\Anaheim.txt")}
930, 931, 934 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\SantaBarbara.txt")}
932, 933, 935 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\Bakersfield.txt")}
936, 937, 938 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\Fresno.txt")}
939, 950, 951 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\SanJose.txt")}
940, 941, 943, 944, 949, 954 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\SanFrancisco.txt")}
942, 952, 953, 956, 957,958,959 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\Sacramento.txt")}
945..948 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\Oakland.txt")}
955 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\Eureka.txt")}
960 | ForEach-Object{$CityHT.Add($_.ToString(),"J:\Redding.txt")}


#Get content and perform matching
$Content = Select-String -Path "J:\*.txt" -Pattern ".{153}(\d{3})[0-9]{2,}"
#Add extra codes to Reno
$Content.Matches.Groups.Captures|?{$_.Name -eq '1' -and $_.Value -notin $CityHT.Keys}|Select -ExpandProperty Value -Unique|ForEach-Object{$CityHT.Add($_,"J:\Reno.txt")}
#Add content to files by matches
$Content|ForEach-Object{Add-Content -Value $_.Line -path $CityHT[$_.Matches.Groups[1].Value]}