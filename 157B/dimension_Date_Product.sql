SELECT years as Quantity, 
	(SELECT sum(quantity) 
		FROM forum.quantity, forum.date, forum.product
		WHERE dateID = date.id
			AND productID = product.id
			AND category = "Individual Sports"
			AND D.years = years
	) as "Individual Sports",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.date, forum.product
		WHERE dateID = date.id
			AND productID = product.id
			AND category = "Team Sports"
			AND D.years = years
	)as "Team Sports",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.date, forum.product
		WHERE dateID = date.id
			AND productID = product.id
			AND category = "Water Sports"
			AND D.years = years
	)as "Water Sports"
	FROM forum.date D
	GROUP BY years;