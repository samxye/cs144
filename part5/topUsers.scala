val lines = sc.textFile("twitter.edges")
val user_following = lines.map( line => line.split(": ") )
val user_follower = user_following.flatMap( arr => {
    val follower = arr(0)
    val users = arr(1)
    val users_arr = users.split(",")
    users_arr.map( user => ( user, follower))
})
val user_1 = user_follower.map( { case (a,b) => (a, 1) } )
val user_count = user_1.reduceByKey((a,b) => a+b)
val user_count_filtered = user_count.filter( { case (a, b) => b > 1000 })
user_count_filtered.saveAsTextFile("output")
System.exit(0)
