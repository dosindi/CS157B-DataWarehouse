SELECT City as Quantity, 
	(SELECT sum(quantity) 
		FROM forum.quantity, forum.store, forum.product
		WHERE storeID = store.id
			AND productID = product.id
			AND category = "Team Sports"
			AND S.city = city
	) as "Team Sports",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.product
		WHERE storeID = store.id
			AND productID = product.id
			AND category = "Individual Sports"
			AND S.city = city
	)as "Individual Sports",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.product
		WHERE storeID = store.id
			AND productID = product.id
			AND category = "Water Sports"
			AND S.city = city
	)as "Water Sports"
	FROM forum.Store S
	WHERE S.state = "California"
	GROUP BY city;