# ZyLOG
Analyze and display ZyWALL log data.

Data can be received from e-mail or read from text file (format needs to be same as sent via e-mail).

Example:
> No.  Date/Time           Source                 Destination           
>      Priority            Category               Note                  
>      Message
> 1    2016-10-26 08:07:15 10.100.100.100:31081   80.200.180.40:2323    
>      notice              ZyWall               ACCESS BLOCK                                    
>      Match default rule, DROP
> 2    2016-10-26 08:07:27 10.100.100.100:31081   80.200.180.40:23      
>      alert               ZyWall               ACCESS BLOCK                                    
>      priority:1, from WAN to ZyWALL, TCP, service Standard_Ports, DROP [count=3]
> 3    2016-10-26 08:07:37 200.40.100.100:42039   80.200.180.40:23      
>      alert               ZyWall               ACCESS BLOCK                                    
>      priority:1, from WAN to ZyWALL, TCP, service Standard_Ports, DROP
> 4    2016-10-26 08:07:43 14.160.140.100:31081   80.200.180.40:23      
>      alert               ZyWall               ACCESS BLOCK                                    
>      priority:1, from WAN to ZyWALL, TCP, service Standard_Ports, DROP [count=4]
> 5    2016-10-26 08:07:55 14.160.140.190:31081   80.200.180.40:23      
>      alert               ZyWall               ACCESS BLOCK                                    
>      priority:1, from WAN to ZyWALL, TCP, service Standard_Ports, DROP [count=3]
> 6    2016-10-26 08:08:06 14.160.100.190:31081   80.200.180.40:23      
>      alert               ZyWall               ACCESS BLOCK                                    
>      priority:1, from WAN to ZyWALL, TCP, service Standard_Ports, DROP [count=2]
> 7    2016-10-26 08:08:18 14.160.140.190:31081   80.200.180.40:23      
>      alert               ZyWall               ACCESS BLOCK                                    
>      priority:1, from WAN to ZyWALL, TCP, service Standard_Ports, DROP
> 8    2016-10-26 08:08:36 14.160.140.190:31081   80.200.180.40:23      
>      alert               ZyWall               ACCESS BLOCK                                    
>      priority:1, from WAN to ZyWALL, TCP, service Standard_Ports, DROP
> 9    2016-10-26 08:09:03 190.100.100.110:5231   80.200.180.40:5060    
>      notice              ZyWall               ACCESS BLOCK                                    
>      Match default rule, DROP
> End of Logs

All fields are processed and available for further analysis.
